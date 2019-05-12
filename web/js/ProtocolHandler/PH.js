class PH {

    static handl(resp){
        let obj = JSON.parse(resp);
        let code = obj.code;
        switch (code) {
            case 130: this.handl_130(); break;
            case 150: this.handl_150(); break;
            case 161: this.handl_161(); break;
        }
    }

    static handl_130(){
        if (SST.checkWaitingResponse(130)){
            SST.fixReceptionAnswer(130);
            Timer.clearTimer_30();
            UC.req_20();
            return;
        }
    }

    static handl_161(){
        if (SST.checkErr()) return;
        SST.setCriticalErr();
        Timer.clearAllTimers();
        UC.err_161();
    }

    static handl_150(){
        if (SST.checkErr()) return;
        SST.setNonCriticalErr();
        Timer.clearAllTimers();
        UC.err_150();
    }
}
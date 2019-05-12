class PH {

    static handl(resp){
        var obj = JSON.parse(resp);
        let code = obj.code;
        switch (code) {
            case 120: this.handl_120(obj.data); break;
            case 130: this.handl_130(); break;
            case 150: this.handl_150(); break;
            case 161: this.handl_161(); break;
        }
    }

    static handl_120(data){
        if (SST.checkErr() || !SST.checkWaitingResponse(120)) return;
        SST.fixReceptionAnswer(120);
        Timer.clearTimer_20();
        Painter.init();
        Painter.AddInDialogList(JSON.parse(data));
        Painter.showDialogList();
        SST.setHandshake(true);
    }

    static handl_130(){
        if (SST.checkErr() || SST.checkWaitingResponse(130)){
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
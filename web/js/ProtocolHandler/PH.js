class PH {

    static handl(resp){
        var obj = JSON.parse(resp);
        let code = obj.code;
        switch (code) {
            case 120: this.handl_120(obj.data); break;
            case 121: this.handl_121(obj.data); break;
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

    static handl_121(data){
        data = JSON.parse(data);
        if (SST.checkErr() || !SST.checkWaitingResponse(121)) return;
        SST.fixReceptionAnswer(121);
        Timer.clearTimer_21();
        Painter.AddInDialog(data.lastMsgInf, data.messageInfList);
        /* Производим проверку того, нужно ли отправлять запрос 1 на сервер */
        let my_id = SST.getId();
        let from_user_id = data.lastMsgInf.from_user_id;
        if (my_id != from_user_id){
            let last_msg_time = new Date(data.lastMsgInf.last_msg_time);
            let my_last_reading_time = new Date(data.lastMsgInf.my_last_reading_time);
            if (last_msg_time > my_last_reading_time)
                Sender.send_1();
        }
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
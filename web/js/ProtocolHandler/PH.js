class PH {

    static handl(resp){
        var obj = JSON.parse(resp);
        let code = obj.code;
        switch (code) {
            case 1: this.handl_1(obj); break;
            case 10: this.handl_10(obj); break;
            case 110: this.handl_110(); break;
            case 111: this.handl_111(obj); break;
            case 120: this.handl_120(obj.data); break;
            case 121: this.handl_121(obj.data); break;
            case 130: this.handl_130(); break;
            case 150: this.handl_150(); break;
            case 161: this.handl_161(); break;
        }
    }

    /**
     *
     * @param pquery
     */
    static handl_1(pquery){
        console.log("handl_1");
        console.log(pquery);
        let fromMe = SST.getId() == pquery.from;
        Painter.ChangeToReadClassInDialogList(pquery.dialog_id, fromMe);
        if (pquery.dialog_id != SST.getCurrentDialog()) return;
        Painter.ChangeToReadClassInDialog();
    }

    /**
     * Прием сообщения от сервера (сообщение отправлено каким-то пользователем)
     * @param data - информация о запросе(в частности, здесь же содержится и сообщение)
     */
    static handl_10(pquery){
        console.log("handl_10");
        if (!SST.checkHandshake()) return;
        if (SST.checkErr()) return;
        let from_user_id = pquery.from;
        let from_dialog_id = pquery.dialog_id;
        let data = JSON.parse(pquery.data);
        let from_user_name = data.name;
        let from_user_avatar = data.avatar;
        let msg = data.msg;
        switch (SST.getWaitingResponse()){
            case -1: PH.h10_0(from_user_id, from_dialog_id, from_user_name, from_user_avatar, msg); break;
            case 10: PH.h10_1(from_user_id, from_dialog_id, from_user_name, from_user_avatar, msg); break;
            case 21: PH.h10_2(from_user_id, from_dialog_id, from_user_name, from_user_avatar, msg); break;
        }
        Painter.scrollDown();
    }

    static handl_110(data){
        if (SST.checkErr() || !SST.checkWaitingResponse(110)) return;
        SST.fixReceptionAnswer(110);
        Timer.clearTimer_10();
        ///Painter.AddMyMessage();
    }

    static handl_111(data)
    {
        console.log("handl_111");
        if (SST.checkErr() || !SST.checkWaitingResponse(111)) return;
        SST.fixReceptionAnswer(111);
        Timer.clearTimer_11();
        data = JSON.parse(data.data);
        SST.setUser(data.user_id, data.name, data.login, data.email,
            data.avatar);
        UC.req_20();
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
            if (last_msg_time >= my_last_reading_time)
                Sender.send_1();
        }

    }

    static handl_130(){
        if (SST.checkErr() || SST.checkWaitingResponse(130)){
            SST.fixReceptionAnswer(130);
            Timer.clearTimer_30();
            ///UC.req_20();
            UC.req_11();
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

    static h10_0(from_user_id, from_dialog_id, from_user_name, from_user_avatar, msg){
        let current_dialog_id = SST.getCurrentDialog();
        let fromMe = from_user_id == SST.getId();
        Painter.AddMessageInDialogList(from_user_id, from_dialog_id, from_user_name, from_user_avatar, msg, fromMe);
        if (current_dialog_id != from_dialog_id) return;
        if (fromMe) {
            Painter.AddMyMessage(msg);
            return;
        }
        Painter.AddMessageInDialog(from_dialog_id, from_user_id, from_user_name, from_user_avatar, msg);
        Sender.send_1();
    }

    static h10_1(from_user_id, from_dialog_id, from_user_name, from_user_avatar, msg){
        this.h10_0(from_user_id, from_dialog_id, from_user_name, from_user_avatar, msg);
    }

    static h10_2(from_user_id, from_dialog_id, from_user_name, from_user_avatar, msg){
        let fromMe = from_user_id == SST.getId();
        Painter.AddMessageInDialogList(from_user_id, from_dialog_id, from_user_name, from_user_avatar, msg, fromMe);
    }

}
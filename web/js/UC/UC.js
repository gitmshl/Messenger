class UC{
    static start(withoutTimerFinit = false){
        console.log("UC.start()");
        SST.init();
        if (!withoutTimerFinit) Timer.init();
        Sender.send_30();
        SST.fixSendingRequest(30);
        Timer.setTimer_30();
    }

    /**
     * Отправка сообщения (нажатие на кнопку enter в диалоге, в поле отправки сообщения)
     */
    static req_10(){
        console.log("UC.req_10");
        /// если мы не в диалоге, то ничего не делаем (эта часть, впринципе, не должна сработать)
        if (SST.getCurrentDialog() < 0) return;
        Painter.blockSending();
        Sender.send_10(Painter.getMsg());
        SST.fixSendingRequest(10);
        Timer.setTimer_10();
    }

    static req_20(){
        console.log("UC.rec_20()");
        if (!SST.checkCame(30)) return;
        Sender.send_20();
        SST.fixSendingRequest(20);
        Timer.setTimer_20();
    }

    static goToDialog(dialog_id){
        console.log("UC.goToDialog");
        if (!SST.handshake) return;
        Painter.FromDialogListToDialog(dialog_id);
        SST.setCurrentDialog(dialog_id);
        Sender.send_21(dialog_id);
        SST.fixSendingRequest(21);
        Timer.setTimer_21();
    }

    static err_150(){
        console.log("UC.err_150()");
    }

    static err_161(){
        console.log("UC.err_161()");
    }

    static err_timer_10(){
        console.log("err_timer_10");
    }

    static err_timer_20(){
        console.log("err_timer_20");
    }

    static err_timer_21(){
        console.log("err_timer_21");
    }

    static err_timer_30(){
        console.log("err_timer_30");
    }
}
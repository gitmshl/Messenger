class UC{
    static start(withoutTimerFinit = false){
        console.log("UC.start()");
        SST.init();
        if (!withoutTimerFinit) Timer.init();
        Sender.send_30();
        SST.fixSendingRequest(30);
        Timer.setTimer_30();
    }

    static req_20(){
        console.log("UC.rec_20()");
        if (!SST.checkCame(30)) return;
        Sender.send_20();
        SST.fixSendingRequest(20);
        Timer.setTimer_20();
    }

    static err_150(){
        console.log("UC.err_150()");
    }

    static err_161(){
        console.log("UC.err_161()");
    }

    static err_timer_20(){
        console.log("err_timer_20");
    }

    static err_timer_30(){
        console.log("err_timer_30");
    }
}
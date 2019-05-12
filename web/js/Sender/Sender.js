class Sender{

    static ws = null;

    static setWs(ws){
        this.ws = ws;
    }

    static send_20(){
        console.log("Sender.send_20()");
        let user_id = SST.getId();
        Sender.send_dt_TEXT(20, user_id, -1, "");
    }

    static send_30(){
        console.log("Sender.send_30()");
        let user_id = SST.getId();
        this.send_dt_TEXT(30, user_id, -1, "");
    }

    static send_dt_TEXT(code, from, dialog_id, data){
        if (this.ws) {
            let msg = {
                code: code,
                from: from,
                dialog_id: dialog_id,
                data_type: Consts.DATA_TYPE_TEXT,
                data: data
            }
            this.ws.send(JSON.stringify(msg));
        }
    }
}
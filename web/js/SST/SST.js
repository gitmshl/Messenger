class SST{

    static init(){
        console.log("SST.init()");
        this.Flags[10] = false;
        this.Flags[20] = false;
        this.Flags[21] = false;
        this.Flags[30] = false;
        this.Come = [false, false]; /// Come[0] - 20 request, Come[1] - 30 request
        this.err_critical = this.err_noncritical = false;
        this.handshake = false;
        this.current_dialog = -1;
    }

    /**
     * Фиксирует отправку клиентского запроса (например, запроса 10).
     * @param code - код протокола. Т.к. это клиентский протокол, то
     *                                                      code < 100
     */
    static fixSendingRequest(code){
        console.log("fixSendingRequest(code = " + code + " )");
        this.Flags[code] = true;
    }

    /**
     * Проверяет, ожидается ли запрос code (ожидание запроса происходит при
     * отправке клиентского запроса на сервер).
     * @param code - т.к. это код ожидания от сервера, то code >= 100
     */
    static checkWaitingResponse(code){
        return this.Flags[code - 100];
    }

    /**
     * Фиксирует приход ответа от сервера.
     * @param code - код ответа от сервера, значит, code > 100
     */
    static fixReceptionAnswer(code){
        code -= 100;
        this.Flags[code] = false;
        if (code == 30){
            this.Come[1] = true;
            return;
        }
        if (code == 20){
            this.Come[0] = true;
            return;
        }

    }

    /**
     * устанавливает критическую ошибку (значит, сервер прислал сообщение о
     * критической ошибке)
     */
    static setCriticalErr(){
        this.err_critical = true;
    }

    static setNonCriticalErr(){
        this.err_noncritical = true;
    }

    /**
     * Производит проверку того, зафиксирована ли от сервера какая-либо ошибка.
     * @return true, если такая ошибка зафиксирована
     * @return false, если ошибки нет
     */
    static checkErr(){
        return this.err_critical || this.err_noncritical;
    }

    /**
     * Проверяет, пришел ли запрос (В прошлом) 20 или 30
     * @param code - либо 20, либо 30
     * @return undefined, если @param не равен 20 и 30 (т.е. ошибка)
     * @return true, если пришел запрос code = {20 or 30}
     * @return false, не приходил
     */
    static checkCame(code){
        if (code != 20 && code != 30) return;
        if (code == 20)
            return this.Come[0];
        return this.Come[1];
    }

    /**
     * Устанавливает рупожожатие
     * @param flag - на него и устаналивается handshake
     */
    static setHandshake(flag = true){
        console.log("SST.setHandshake");
        this.handshake = flag;
    }

    static checkHandshake(){
        return this.handshake;
    }

    static setCurrentDialog(dialog_id){
        SST.current_dialog = dialog_id;
        let dialog_block = document.getElementById(dialog_id);
        let dialog_name = dialog_block.getElementsByClassName("sub1")[0].textContent;
        let dialog_img = dialog_block.getElementsByClassName("avatar_block")[0].getElementsByTagName("img")[0].src
        SST.DialogInformation.name = dialog_name;
        SST.DialogInformation.img = dialog_img;
    }

    static getCurrentDialog(){
        return this.current_dialog;
    }

    static setUser(id, name="", login="", email="", avatar="standart.jpg"){
        this.User.id = id;
        this.User.name = name;
        this.User.login = login;
        this.User.email = email;
        this.User.avatar = avatar;
    }

    static getId(){
        return this.User.id;
    }

    static getName(){
        return this.User.name;
    }

    static getAvatar(){
        return this.User.avatar;
    }

    /**
     * Возвращает код запроса ( < 100), который ожидается. Если ничего не ожидается, возвращается -1
     */
    static getWaitingResponse(){
        SST.Flags.forEach(function(flag, id){
            if (flag) return id;
        });
        return -1;
    }

    static User = {
        id: -1,
        name: "",
        email: "",
        login: "",
        avatar: ""
    }

    static DialogInformation = {
        name: "",
        img: ""
    }

    static Flags = [];
    static Come; /// Come[0] - 20 request, Come[1] - 30 request
    static err_critical;
    static err_noncritical;
    static handshake;
    static current_dialog;
}
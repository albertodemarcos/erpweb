export class AutenticacionRequest {
    username: string;
    password: string;

    constructor(usernameParam: string, passwordParam: string){
        this.username = usernameParam;
        this.password = passwordParam;
    }
}
import * as _ from 'lodash';

const USER_ROLES = {
    ADMIN: 'admin',
    USER: 'user',
};

export class User {
    /*constructor() {
        this.username = null;
        this.password = null;
        this.firstname = null;
        this.lastname = null;
        this.email = null;
        this.address = null;
        this.phone = null;
    }*/

    constructor(data) {
        _.assignWith(this, data);
    }
    
    /*reset() {
        this.username = null;
        this.password = null;
        this.firstname = null;
        this.lastname = null;
        this.email = null;
        this.address = null;
        this.phone = null;
    }*/

    static get USER_ROLES() {
        return USER_ROLES;
    }

    isAdmin() {
        return this.role === USER_ROLES.ADMIN;
    }
}
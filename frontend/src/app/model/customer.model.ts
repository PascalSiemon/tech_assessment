import { Account } from "./account.model";

export class Customer {

    constructor(accounts: Account[]) {
        this.accounts = accounts;
    }

    accounts: Account[];

}
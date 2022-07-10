export class Account {

    constructor(accountType: string, currency: string, amount: number) {
        this.accountType = accountType;
        this.currency = currency;
        this.amount = amount;
    }

    accountType: string;
    currency: string;
    amount: number;

}
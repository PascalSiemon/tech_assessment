import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {map} from 'rxjs/operators'
import { Customer } from '../model/customer.model';
import { Account } from '../model/account.model';

@Injectable({
    providedIn: 'root'
})
export class BalanceService {

    private accountEndpoint: string = 'http://localhost:8080/accounts';

    constructor(private httpClient: HttpClient) { }

    getCustomerAccounts(): Observable<Customer> {
        return this.httpClient.get(this.accountEndpoint).pipe(
            map((result) => {
                return <Customer> result;
            }
        ));
    }

}
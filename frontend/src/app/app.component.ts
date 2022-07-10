import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { BalanceService } from 'src/app/service/balance.service';
import { Customer } from './model/customer.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  total: number = 0;
  defaultCurrency: string = 'GBP';

  customer?: Customer = new Customer([]);
  
  constructor(private balanceService: BalanceService) { }

  ngOnInit() {
    this.balanceService.getCustomerAccounts().subscribe((customer) => {
      this.customer = customer;
      customer.accounts.forEach(account => this.total = this.total + account.amount);
      this.total = this.roundAmount(this.total);
      this.customer.accounts.forEach(account => account.amount = this.roundAmount(account.amount));
    });
  }

  private roundAmount(amount: number): number {
    return Math.round(amount*20)/20;
  }

}

import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable()
export class ShareService {
  private userAnswerList = new BehaviorSubject<string[]>([]);
  constructor() {}

  setUserAnswerList(userAnswerList: string[]) {
    this.userAnswerList.next(userAnswerList);
    console.log("In ShareService after setting:" + this.userAnswerList);
  }
  getUserAnswerList$():Observable<string[]>{
    return this.userAnswerList.asObservable();
  }
}
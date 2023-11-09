import { Injectable } from '@angular/core';
import { Triviainformation } from './trivia-information';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Results } from './results';

@Injectable({
  providedIn: 'root',
})
export class DataService {

  private getQuestionsUrl: string;
  private getResultsUrl: string;

  constructor(private http: HttpClient){
    this.getQuestionsUrl = 'http://localhost:8080/v1/questions';
    this.getResultsUrl = 'http://localhost:8080/v1/answers';
  }

 public getAllQuestionsInformation(): Observable<Triviainformation[]> {
  return this.http.get<Triviainformation[]>(this.getQuestionsUrl);
  }

  public getResults(userAnswers: string[]): Observable<Results> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json; charset=utf-8'});
    console.log("In In Dataservice inside getResults, userAnswers: " + userAnswers);
    return this.http.post<Results>(this.getResultsUrl, {userAnswers}, { headers }); 
    }
}
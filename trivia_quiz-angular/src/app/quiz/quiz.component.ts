import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Triviainformation } from '../trivia-information';
import { DataService } from '../data.service';
import {HttpClientModule } from '@angular/common/http';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { Router } from '@angular/router';
import { ShareService } from '../share.service';

@Component({
  selector: 'app-quiz',
  standalone: true,
  imports: [CommonModule,HttpClientModule,RouterLink],
  template: `
  <h1>Question {{counter}}</h1>
  <body class= category>Category: {{triviaInformationList[counter-1].category}} </body>
  <body class= difficulty>Difficulty: {{triviaInformationList[counter-1].difficulty}} </body>
  <body class = question>Question: {{triviaInformationList[counter-1].question}}</body>
  <button class ='answer1' (click)="add(triviaInformationList[counter-1].answers[0])"> {{triviaInformationList[counter-1].answers[0]}} </button>
  <button class ='answer2' (click)="add(triviaInformationList[counter-1].answers[1])">{{triviaInformationList[counter-1].answers[1]}} </button>
  <button class ='answer3' (click)="add(triviaInformationList[counter-1].answers[2])">{{triviaInformationList[counter-1].answers[2]}} </button>
  <button class ='answer4' (click)="add(triviaInformationList[counter-1].answers[3])">{{triviaInformationList[counter-1].answers[3]}} </button>
`,
  styleUrls: ['./quiz.component.css']
})
export class QuizComponent implements OnInit {
  triviaInformationList: Triviainformation[]=[];
  userAnswerList: string[] = [];
  counter: number = 1; 

  constructor(private dataService: DataService,
              private router: Router, private shareService: ShareService){}

  ngOnInit(){
  this.dataService.getAllQuestionsInformation().subscribe(data => {
    this.triviaInformationList = data;
  });
  return this.triviaInformationList;
}

  add(userAnswer: string) {
    if (this.counter < 5) {
      this.userAnswerList[this.counter-1] = userAnswer;
      this.counter++;
    }
    else {
      this.userAnswerList[this.counter-1] = userAnswer;
      this.shareService.setUserAnswerList(this.userAnswerList);
      this.router.navigate(['/results']);
    }
  }
}
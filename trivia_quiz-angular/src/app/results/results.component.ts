import { Component, OnInit} from '@angular/core';
import { ShareService } from '../share.service';
import { DataService } from '../data.service';
import { Results } from '../results';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-results',
  standalone: true,
  imports: [CommonModule],
  template: `
  <H2>The results of your quiz!</H2>
  <H1 class = "results"> Your score: {{results.percentageCorrect}}% </H1>
  <body class ="questionHeader">Questions answered incorrectly:</body>
  <div *ngFor="let question of results.questionsIncorrect; let i = index">
      <body class ="incorrectQuestions">Question: {{ results.questionsIncorrect[i] }}</body>
      <body class = "correctAnswers">Correct answer: {{ results.correctedAnswers[i] }}</body>
  </div>
  `,
  styleUrls: ['./results.component.css']
})
export class ResultsComponent implements OnInit {
  userAnswerList : string[]=["check", "check3"];
   results: Results = {
    percentageCorrect: 0, 
    questionsIncorrect: ["Question 2", "Question 4"],
    correctedAnswers: ["Answer A", "Answer C"],
  };
  constructor(private shareService: ShareService, private dataService: DataService){  
    this.shareService.getUserAnswerList$().subscribe(data => {
      this.userAnswerList = data;
    })
  }

  ngOnInit(){
    this.dataService.getResults(this.userAnswerList).subscribe(data => {
    console.log("In ResultComponent inside ngOnInit, userAnswerList: " + this.userAnswerList);
      this.results = data; 
    });
    console.log("In ResultComponent after ngOnInit, results: " + this.results);
    console.log("In ResultComponent after ngOnInit, userAnswerList: " + this.userAnswerList);
    return this.results;
  }
}
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-rules',
  standalone: true,
  imports: [CommonModule, HttpClientModule],
  template: `
    <h1>Welcome to the Quiz! Here are the rules:</h1>
    <body>1: Do not look up the answers on the internet </body>
    <body>2: Take the quiz alone to test your knowledge </body>
    <body>3: Have fun! </body>
  `,
  styleUrls: ['./rules.component.css']
})
export class RulesComponent {
}
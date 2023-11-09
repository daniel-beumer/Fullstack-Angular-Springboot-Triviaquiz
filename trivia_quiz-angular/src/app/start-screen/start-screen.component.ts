import { Component,inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { DataService } from '../data.service';
import { Triviainformation } from '../trivia-information';

@Component({
  selector: 'app-start-screen',
  standalone: true,
  imports: [CommonModule, RouterLink, HttpClientModule],
  template: `
  <div class="start-screen">
    <h1>Welcome to the Quiz!</h1>
    <p>Test your knowledge with our quiz.</p>
    <a [routerLink]="['/rules']">
    <button mat-raised-button color="primary">Info</button>
    </a>
    <a [routerLink]="['/quiz']">
    <button mat-raised-button color="accent">Start</button>
    </a>
  </div>
  `,
  styleUrls: ['./start-screen.component.css']
})
export class StartScreenComponent {
}
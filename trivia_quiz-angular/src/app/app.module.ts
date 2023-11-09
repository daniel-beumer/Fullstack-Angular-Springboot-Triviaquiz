import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { AppComponent } from './app.component';
import { RouterLink, RouterModule } from '@angular/router';
import { RouterLinkActive } from '@angular/router';
import { RouterOutlet } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { QuizComponent } from './quiz/quiz.component';
import { StartScreenComponent } from './start-screen/start-screen.component';
import { RulesComponent } from './rules/rules.component';
import { ResultsComponent } from './results/results.component';
import { ShareService } from './share.service';

@NgModule({
  imports: [
    AppRoutingModule,
    BrowserModule,
    CommonModule,
    RouterLink, 
    RouterLinkActive, 
    RouterOutlet,
    RouterModule,
    HttpClientModule,
    QuizComponent,
    StartScreenComponent,
    RulesComponent,
    ResultsComponent,
  ],
  declarations: [
    AppComponent,
  ],
  providers: [
    QuizComponent,
    ShareService,
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule {}

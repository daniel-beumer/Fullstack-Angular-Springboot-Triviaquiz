
import { Routes } from '@angular/router';
import { StartScreenComponent } from './start-screen/start-screen.component';
import { RulesComponent } from './rules/rules.component';
import { QuizComponent } from './quiz/quiz.component';
import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { ResultsComponent } from './results/results.component';

export const routes: Routes = [
  {
    path: '',
    component: StartScreenComponent,
    title: 'Home page'
  },
  {
    path: 'rules',
    component: RulesComponent,
    title: 'Rules'
  },
  {
    path: 'quiz',
    component: QuizComponent,
    title: 'Quiz'
  },
  {
    path: 'results',
    component: ResultsComponent,
    title: 'Results'
  }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
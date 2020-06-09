import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import {HomeComponent} from 'src/app/components/home/home.component';

const routes: Routes = [
  { path: '**', redirectTo: 'home', pathMatch: 'full'}, // Ruta general
  { path: 'routePath', component: HomeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }

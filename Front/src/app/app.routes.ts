// src/app.routes.ts
import { Routes } from '@angular/router';
import { FacilitiesComponent } from './facilities/facilities.component';

export const routes: Routes = [
  { path: 'facilities', component: FacilitiesComponent },
  { path: '', redirectTo: '/facilities', pathMatch: 'full' },
  { path: '**', redirectTo: '/facilities' }
];

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FacilitiesComponent } from './facilities/facilities.component';
import { CreateFacilityComponent } from './create-facility/create-facility.component';
import { EditFacilityComponent } from './edit-facility/edit-facility.component';
import { FacilityDetailsComponent } from './facility-details/facility-details.component'; // Import the new component

export const routes: Routes = [
  { path: '', redirectTo: '/facilities', pathMatch: 'full' }, // Redirect to default path
  { path: 'facilities', component: FacilitiesComponent },
  { path: 'facilities/create', component: CreateFacilityComponent },
  { path: 'edit-facility/:id', component: EditFacilityComponent },
  { path: 'facilities/:id', component: FacilityDetailsComponent }, // Add route for FacilityDetailsComponent

  // Add other routes here as needed
  { path: '**', redirectTo: '/facilities' } // Wildcard route for a 404 page or redirect
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}


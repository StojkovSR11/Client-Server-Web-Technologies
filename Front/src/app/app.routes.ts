import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FacilitiesComponent } from './facilities/facilities.component';
import { CreateFacilityComponent } from './create-facility/create-facility.component';
import { EditFacilityComponent } from './edit-facility/edit-facility.component';


export const routes: Routes = [
  { path: '', component: FacilitiesComponent },
  { path: 'facilities', component: FacilitiesComponent },
  { path: 'create-facility', component: CreateFacilityComponent },
  { path: 'edit-facility/:id', component: EditFacilityComponent },
  // Add other routes here
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}

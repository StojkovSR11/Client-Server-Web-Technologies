import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FacilitiesComponent } from './facilities/facilities.component';
import { CreateFacilityComponent } from './create-facility/create-facility.component';
import { EditFacilityComponent } from './edit-facility/edit-facility.component';
import { FacilityDetailsComponent } from './facility-details/facility-details.component'; // Import the new component
import { CreateReviewComponent } from './create-review/create-review.component';
import { ExerciseComponent } from './exercise/exercise.component';
import { CreateExerciseComponent } from './create-exercise/create-exercise.component';

export const routes: Routes = [
  { path: '', redirectTo: '/facilities', pathMatch: 'full' }, // Redirect to default path
  { path: 'facilities', component: FacilitiesComponent },
  { path: 'facilities/create-facility', component: CreateFacilityComponent },
  { path: 'edit-facility/:id', component: EditFacilityComponent },
  { path: 'facilities/:id', component: FacilityDetailsComponent }, // Add route for FacilityDetailsComponent
  { path: 'facilities/:id/add-review', component: CreateReviewComponent },
  { path: 'exercises', component: ExerciseComponent },
  { path: 'facilities/:id/add-exercise', component: CreateExerciseComponent },

  // Add other routes here as needed
  { path: '**', redirectTo: '/facilities' } // Wildcard route for a 404 page or redirect
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}


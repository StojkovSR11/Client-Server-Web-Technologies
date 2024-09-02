import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FacilityService } from './facility.service';
import { Facility } from './facility.model';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { FormsModule } from '@angular/forms';  // Dodajte ovaj import

@Component({
  selector: 'app-facilities',
  templateUrl: './facilities.component.html',
  styleUrls: ['./facilities.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule]  // Dodajte FormsModule u imports
})
export class FacilitiesComponent implements OnInit {
  facilities$!: Observable<Facility[]>;
  filteredFacilities$!: Observable<Facility[]>;

  filters = {
    city: '',
    discipline: '',
    minRating: 0,
    maxRating: 5
  };

  constructor(private facilityService: FacilityService) {}

  ngOnInit(): void {
    this.facilities$ = this.facilityService.getFacilities();
    this.filteredFacilities$ = this.facilities$;
  }

  applyFilters(): void {
    this.filteredFacilities$ = this.facilities$.pipe(
      map(facilities =>
        facilities.filter(facility =>
          (this.filters.city ? facility.city.toLowerCase().includes(this.filters.city.toLowerCase()) : true) &&
          (this.filters.discipline ? facility.description?.toLowerCase().includes(this.filters.discipline.toLowerCase()) : true) &&
          (facility.totalRating ? facility.totalRating >= this.filters.minRating : true) &&
          (facility.totalRating ? facility.totalRating <= this.filters.maxRating : true)
        )
      )
    );
  }
}













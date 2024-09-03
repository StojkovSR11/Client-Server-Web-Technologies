import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FacilityService } from '../facilities/facility.service';
import { Facility } from '../facilities/facility.model';
import { DatePipe } from '@angular/common'; // Import DatePipe
import { CommonModule } from '@angular/common'; // Import CommonModule

@Component({
  selector: 'app-facility-details',
  templateUrl: './facility-details.component.html',
  styleUrls: ['./facility-details.component.css'],
  standalone: true,
  imports: [CommonModule], // Import CommonModule for pipes
  providers: [DatePipe] // Provide DatePipe here
})
export class FacilityDetailsComponent implements OnInit {
  facility: Facility | undefined;

  constructor(
    private facilityService: FacilityService,
    private route: ActivatedRoute,
    private router: Router,
    private datePipe: DatePipe // Inject DatePipe here
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.facilityService.getFacilityById(id).subscribe(facility => {
      this.facility = facility;
    });
  }

  formatDate(date: string | undefined): string | null {
    return date ? this.datePipe.transform(date, 'shortDate') : null;
  }

  goBack(): void {
    this.router.navigate(['/facilities']);
  }
}

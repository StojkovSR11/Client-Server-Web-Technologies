import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Facility } from './facility.model';  // Correct path

@Injectable({
  providedIn: 'root'
})
export class FacilityService {
  private apiUrl = 'http://localhost:8080/facilities';

  constructor(private http: HttpClient) {}

  getFacilities(): Observable<Facility[]> {
    console.log('Fetching facilities from:', this.apiUrl);  // Debug log
    return this.http.get<Facility[]>(this.apiUrl);

  }

  getFacilityById(id: number): Observable<Facility> {
    return this.http.get<Facility>(`${this.apiUrl}/${id}`);
  }

  createFacility(facility: Facility): Observable<Facility> {
    return this.http.post<Facility>(this.apiUrl, facility);
  }

  updateFacility(id: number, facility: Facility): Observable<Facility> {
    return this.http.put<Facility>(`${this.apiUrl}/${id}`, facility);
  }

  deleteFacility(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}




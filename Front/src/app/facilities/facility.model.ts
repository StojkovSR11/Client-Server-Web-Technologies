export interface Rate {
  id: number;
  equipment: number;
  staff: number;
  hygiene: number;
  space: number;
}

export interface Review {
  id: number;
  createdAt: string; // Adjusted to string for date formatting
  exerciseCount: number;
  hidden: boolean;
  rate: Rate; // Nested Rate interface
}

export interface WorkDay {
  id: number;
  validFrom: string; // Adjusted to string for date formatting
  day: string;
  fromTime: string; // Adjusted to string for time formatting
  untilTime: string; // Adjusted to string for time formatting
}

export interface Discipline {
  id: number;
  name: string;
}

export interface Facility {
  id: number;
  name: string;
  description?: string;  // Optional field
  createdAt: string; // Adjusted to string for date formatting
  address: string;
  city: string;
  totalRating?: number;  // Optional field
  active: boolean;
  workDays: WorkDay[]; // Array of WorkDay
  disciplines: Discipline[]; // Array of Discipline
  reviews: Review[]; // Array of Review
}




  
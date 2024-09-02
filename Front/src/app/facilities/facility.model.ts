export interface Facility {
  id: number;
  name: string;
  description?: string;  // Optional field
  createdAt: Date;
  address: string;
  city: string;
  totalRating?: number;  // Optional field
  active: boolean;
}


  
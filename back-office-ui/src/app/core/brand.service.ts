import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {BrandQuantity} from '../model/brand.quantity.model';
import {Brand} from "../model/brand.model";

@Injectable()
export class BrandService {

  constructor(private http: HttpClient) {
  }

  baseUrl: string = 'http://localhost:8080/back-office/api/brand/';

  getBrandsQuantity(): Observable<BrandQuantity[]> {
    return this.http.get<BrandQuantity[]>(this.baseUrl + '/quantity');
  }

  getBrand(brandId: number): Observable<Brand> {
    return this.http.get<Brand>(this.baseUrl + brandId);
  }

  updateBrand(brand: Brand): Observable<Brand> {
    return this.http.put<Brand>(this.baseUrl + brand.id, brand);
  }

  deleteBrand(brandId: number): Observable<Brand> {
    return this.http.delete<Brand>(this.baseUrl + brandId);
  }

  addBrand(brand: Brand): Observable<Brand> {
    return this.http.post<Brand>(this.baseUrl, brand);
  }
}

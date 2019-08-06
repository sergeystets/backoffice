import {Component, OnInit, ViewChild} from '@angular/core';
import {Router} from '@angular/router';
import {BrandService} from '../core/brand.service';
import {MatSort, MatTableDataSource} from "@angular/material";
import {BrandQuantity} from "../model/brand.quantity.model";

@Component({
  selector: 'app-brand-quantity',
  templateUrl: './get-brands-quantity.component.html',
  styleUrls: ['./get-brands-quantity.component.css']
})
export class GetBrandsQuantityComponent implements OnInit {

  brandsDataSource: MatTableDataSource<BrandQuantity>;
  brands: BrandQuantity[];
  displayedColumns: string[] = ['brandId', 'brandName', 'quantity', 'actions'];

  @ViewChild(MatSort) sort: MatSort;

  constructor(private router: Router, private brandService: BrandService) {
  }

  ngOnInit() {
    this.brandService.getBrandsQuantity()
      .subscribe(brands => {
        this.brandsDataSource = new MatTableDataSource(brands);
        this.brands = brands;
        this.brandsDataSource.sort = this.sort;
      });
  }

  updateBrand(brandId: number): void {
    this.router.navigate(['brand/' + brandId + '/update']);
  };

  deleteBrand(brandId: number): void {
    this.brandService.deleteBrand(brandId)
      .subscribe(() => {
        this.brands = this.brands.filter(u => u.brandId !== brandId);
        this.brandsDataSource = new MatTableDataSource<BrandQuantity>(this.brands);
      })
  };

  addBrand(): void {
    this.router.navigate(['brand/add']);
  };
}

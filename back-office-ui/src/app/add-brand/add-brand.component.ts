import {Component, OnInit} from '@angular/core';
import {Brand} from "../model/brand.model";
import {first} from "rxjs/operators";
import {BrandService} from "../core/brand.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-brand',
  templateUrl: './add-brand.component.html',
  styleUrls: ['./add-brand.component.css']
})
export class AddBrandComponent implements OnInit {

  model: Brand = {id: null, name: ""};

  constructor(private router: Router,
              private brandService: BrandService) {
  }

  ngOnInit() {
  }

  onSubmit() {
    this.brandService.addBrand(this.model).pipe(first()).subscribe(() => {
      this.router.navigate(['brand/quantity']);
    })
  }

  onCancel() {
    this.router.navigate(['brand/quantity']);
  }
}

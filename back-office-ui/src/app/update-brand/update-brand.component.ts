import {Component, OnInit} from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {first} from "rxjs/operators";
import {BrandService} from "../core/brand.service";
import {Brand} from "../model/brand.model";

@Component({
  selector: 'app-edit-brand',
  templateUrl: './update-brand.component.html',
  styleUrls: ['./update-brand.component.css']
})
export class UpdateBrandComponent implements OnInit {

  model: Brand = {id: null, name: ""};

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private brandService: BrandService,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.params.subscribe(params =>
      this.brandService.getBrand(+params['id'])
        .subscribe(brand => {
          this.model = brand;
        })
    );
  }

  onSubmit() {
    this.brandService.updateBrand(this.model).pipe(first()).subscribe(() => {
      this.router.navigate(['brand/quantity']);
    })
  }

  onCancel() {
    this.router.navigate(['brand/quantity']);
  }
}

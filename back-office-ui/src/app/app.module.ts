import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {routing} from './app-routing.module';
import {AppComponent} from './app.component';
import {GetBrandsQuantityComponent} from './get-brands-quantity/get-brands-quantity.component';
import {BrandService} from './core/brand.service';
import {HttpClientModule} from '@angular/common/http';
import {UpdateBrandComponent} from './update-brand/update-brand.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AddBrandComponent} from './add-brand/add-brand.component';
import {MaterialModule} from "./material.module";
import {FlexLayoutModule} from "@angular/flex-layout";

@NgModule({
  declarations: [
    AppComponent,
    GetBrandsQuantityComponent,
    UpdateBrandComponent,
    AddBrandComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    routing,
    MaterialModule,
    FlexLayoutModule
  ],
  providers: [BrandService],
  bootstrap: [AppComponent]
})
export class AppModule {
}

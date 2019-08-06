import {RouterModule, Routes} from '@angular/router';
import {GetBrandsQuantityComponent} from './get-brands-quantity/get-brands-quantity.component';
import {UpdateBrandComponent} from "./update-brand/update-brand.component";
import {AddBrandComponent} from "./add-brand/add-brand.component";

const routes: Routes = [
  {path: '', component: GetBrandsQuantityComponent},
  {path: 'brand/quantity', component: GetBrandsQuantityComponent},
  {path: 'brand/:id/update', component: UpdateBrandComponent},
  {path: 'brand/add', component: AddBrandComponent},
];

export const routing = RouterModule.forRoot(routes);

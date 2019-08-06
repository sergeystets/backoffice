import {NgModule} from '@angular/core';
import {
  MatButtonModule,
  MatButtonToggleModule,
  MatCardModule,
  MatFormFieldModule,
  MatIconModule,
  MatInputModule,
  MatSortModule,
  MatTableModule,
  MatToolbarModule
} from "@angular/material";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

@NgModule({
  declarations: [],
  imports: [],
  providers: [],
  bootstrap: [],
  exports: [
    MatTableModule,
    MatSortModule,
    BrowserAnimationsModule,
    MatButtonToggleModule,
    MatButtonModule,
    MatIconModule,
    MatFormFieldModule,
    MatToolbarModule,
    MatInputModule,
    MatCardModule]
})
export class MaterialModule {
}

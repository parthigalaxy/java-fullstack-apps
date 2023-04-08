import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SearchProfileComponent } from './search-profile/search-profile.component';
import { FormsModule } from '@angular/forms'; 
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatDividerModule} from '@angular/material/divider';
import { SkillLoginComponent } from './skill-login/skill-login.component';

@NgModule({
  declarations: [
    AppComponent,
    SearchProfileComponent,
    SkillLoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    MatInputModule,
    MatFormFieldModule,
    FormsModule,
    ReactiveFormsModule,
    MatSelectModule,
    MatTableModule,
    MatPaginatorModule,
    MatSnackBarModule,
    MatExpansionModule,
    MatProgressBarModule,
    MatDividerModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

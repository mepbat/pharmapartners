import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { LoginComponent } from './login/login.component';
import { AppointmentSearchComponent } from './appointment-search/appointment-search.component';
import { AgendaComponent } from './agenda/agenda.component';
import { ClientOverviewComponent } from './client-overview/client-overview.component';
import { AuthguardService } from './shared/services/authGuard/authguard.service';
import { AgendaEmployeeComponent } from './agenda-employee/agenda-employee.component';
import { VerifyCodeComponent } from './verify-code/verify-code.component';
import { ClientDossierComponent } from './client-dossier/client-dossier.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'home', component: AgendaEmployeeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'verify', component: VerifyCodeComponent, canActivate: [AuthguardService] },
  { path: 'agenda', component: AgendaComponent, canActivate: [AuthguardService] },
  { path: 'agenda/:id', component: AgendaComponent, canActivate: [AuthguardService] },
  { path: 'appointmentsearch', component: AppointmentSearchComponent, canActivate: [AuthguardService] },
  { path: 'clientoverview', component: ClientOverviewComponent, canActivate: [AuthguardService] },
  { path: 'clientoverview/:id', component: ClientOverviewComponent, canActivate: [AuthguardService] },
  { path: 'clientdossier/:id', component: ClientDossierComponent, canActivate: [AuthguardService] },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(
      routes,
      { enableTracing: false } // <-- debugging purposes only
    ),
  ]
})
export class RoutingController {
}

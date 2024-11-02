import { provideRouter, RouterModule, Routes } from '@angular/router';

import { NgModule } from '@angular/core';
import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { BienvenidaComponent } from './components/bienvenida/bienvenida.component';
import { FacturasComponent } from './components/facturas/facturas.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { UserdatosComponent } from './components/userdatos/userdatos.component';
import { TicketComponent } from './components/ticket/ticket.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AuthGuard } from './guards/auth.guard';

export const routes: Routes = [

    { path: 'bienvenida', component: BienvenidaComponent},

    { path: 'facturas', component: FacturasComponent,canActivate: [AuthGuard] },

    { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard]},

    { path: 'userdatos', component: UserdatosComponent, canActivate: [AuthGuard] },

    { path: 'ticket', component: TicketComponent,canActivate: [AuthGuard] },
    
    { path: 'login', component: LoginComponent},

    { path: 'register', component: RegisterComponent },

    
    {path: '**',  redirectTo: '/bienvenida', pathMatch: 'full'}

];


  bootstrapApplication(AppComponent, {
    providers:[
        provideRouter(routes)
    ]
});
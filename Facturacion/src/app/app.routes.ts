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

export const routes: Routes = [

    { path: 'bienvenida', component: BienvenidaComponent},

    { path: 'facturas', component: FacturasComponent },

    { path: 'dashboard', component: DashboardComponent },

    { path: 'userdatos', component: UserdatosComponent },

    { path: 'ticket', component: TicketComponent },
    
    { path: 'login', component: LoginComponent},

    { path: 'register', component: RegisterComponent },

    
    {path: '**', component: HomeComponent}

];


  bootstrapApplication(AppComponent, {
    providers:[
        provideRouter(routes)
    ]
});
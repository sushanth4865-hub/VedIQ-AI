
import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    title: 'VedIQ AI | Learn STEM Visually',
    loadComponent: () =>
      import('./features/home/home.component')
        .then((module) => module.HomeComponent)
  }
];

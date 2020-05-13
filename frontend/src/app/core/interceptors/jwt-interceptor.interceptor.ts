import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../services/authentication.service';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
    constructor(
        private authenticationService: AuthenticationService
      ) {}
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        const item = this.authenticationService.getToken();
        const decodedItem = JSON.parse(item);

        if (item) {
            const cloned = req.clone({
                headers: req.headers.set('Authorization', 'Bearer ' + decodedItem)
            });

            return next.handle(cloned);
        } else {
            return next.handle(req);
        }
    }

}

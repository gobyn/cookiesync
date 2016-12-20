#import <Cordova/CDV.h>

@interface CookieSync : CDVPlugin

- (void)sync : (CDVInvokedUrlCommand*)command;
- (void)persistCookies: (CDVInvokedUrlCommand*)command;
- (void)restoreCookies: (CDVInvokedUrlCommand*)command;
@end
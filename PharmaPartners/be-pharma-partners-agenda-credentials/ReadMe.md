
User registration
/register
Post {username, password, using2Fa}
return if using 2fa -> QRcode image data
       else -> username is returned

User Login
/authenticate
Post {username, password}
return Token (authenticated = false)

User entered TOTP
/verify
Post {code}
return Token (authenticated = true)



/greeting
if the JWT is valid you will receive a welcome message

example used
https://www.techgeeknext.com/spring/spring-boot-security-token-authentication-jwt-mysql




Time-based One Time Password (MFA) Library for Java

https://github.com/samdjstevens/java-totp#generating-a-qr-code


go to http://localhost:4000/barcode for the imageuri

copy image uri and paste in de src of image tag
https://www.w3docs.com/tools/code-editor/10848
example



<div>
      <p>From wikipedia</p>

      <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4
        //8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg==" alt="Red dot" />
</div>
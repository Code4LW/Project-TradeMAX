<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center; color: deepskyblue">Registration</h1>
    <hr>
    <h5 style="color: black"> Fill the fields below:</h5>
<#--<form action = "/signup" method = "post">-->
<#--    Name: <input type="text" name="name"><br><br>-->
<#--    Email: <input type="email" name="email"><br><br>-->
<#--    PhoneNumber<input type="text" name="phoneNumber"><br><br>-->
<#--    Password<input type="password" name="password"><br><br>-->
<#--    <input type="hidden" name="_csrf" value="${_csrf.token}">-->
<#--    <button type="submit" style="width: 100%" class="btn btn-primary">Sign up</button>-->
<#--</form>-->
    <form action="/signup" method="post">
        <div class="form-group">
            <label for="name" style="font-weight: bold">Name</label>
            <input type="text" class="form-control" required id="name" name="name">
        </div>
        <div class="form-group">
            <label for="email" style="font-weight: bold">Email</label>
            <input type="email" class="form-control" id="email" required name="email">
        </div>
        <div class="form-group">
            <label for="phoneNumber" style="font-weight: bold">Phone number</label>
            <input type="text" class="form-control" id="phoneNumber" required name="phoneNumber">
        </div>
        <div class="form-group">
            <label for="password" style="font-weight: bold">Password</label>
            <input type="password" class="form-control" id="password" required name="password">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" style="width: 100%; color: white" class="btn btn-primary">Signup</button>
    </form>
<#if Error??>
    <h2 style="color: red">${Error}</h2>
</#if>
</@t.template>
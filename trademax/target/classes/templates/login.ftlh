<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center; color: black" >Authorization</h1>
    <hr>
<#--<form action = "/login" method = "post">-->
<#--    Your email: <input type="email" name="username"><br><br>-->
<#--    Your password: <input type="password" name="password"><br><br>-->
<#--    <input type="hidden" name="_csrf" value="${_csrf.token}">-->
<#--    <button type="submit" style="width: 100%" class="btn btn-primary">Login</button>-->
<#--</form>-->
    <form action="/login " method="post">
        <div class="form-group">
            <label for="email" style="font-weight: bold">Email</label>
            <input type="email" class="form-control " id="email" name="username">
        </div>
        <div class="form-group">
            <label for="password" style="font-weight: bold">Password</label>
            <input type="password" class="form-control" id="password" name="password">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" style="width: 100%; color: white" class="btn btn-primary">Login</button>
    </form>
    <hr>
    <h5> No account? Then you can <a href="/signup">sign up</a></h5>
</@t.template>

<#import "blocks/template.ftlh" as t>
<@t.template user><
<h1 style="text-align: center"> Admin panel </h1><hr>
<table class="table-hover table-success" style="alignment: center; width: 100%">
    <tr>
        <th>Email:</th>
        <th>Phone number</th>
        <th>Status:</th>
        <th>Role:</th>
        <th>Ban:</th>
        <th>Edit</th>
        <th>User details</th>
    </tr>
    <#list users as user>
        <tr>
            <th>${user.email}:</th>
            <th>${user.phoneNumber}</th>
            <th><#if user.active>true<#else>false</#if></th>
            <th><#list user.roles as role>${role}</#list></th>
            <th>
                <form action="/admin/user/ban/${user.id}" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" value="<#if user.isActive()>Ban<#else> Unban</#if> ">
                </form>
            </th>
            <th> <a href="/admin/user/edit/${user.id}"> Edit user</a></th>
            <th> <a href="/user/${user.id}"> User details</a></th>
        </tr>
        <#else>
        <h3>No users</h3>
    </#list>
</table>
</@t.template>
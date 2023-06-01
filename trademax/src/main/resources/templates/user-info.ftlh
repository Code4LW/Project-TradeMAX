<#import "blocks/template.ftlh" as t>
<@t.template userPrincipal>
    <h1 style="text-align: center">Information about the user:</h1>
    <hr>
<#--<b>Email</b> ${user.email}<br>-->
<#--<b>Phonenumber</b> ${user.phoneNumber}<br>-->
    <div style="text-align: center">
        <h2>${user.name}</h2>
        <br>
        ${user.name}'s phone number: <b>${user.phoneNumber}</b>
    </div>
    <br>
    <hr>
<#--<h4>More ads from the user ${user.name}:</h4>-->
    <h1 style="text-align: center">More ads from ${user.name}</h1>
    <br>
    <div class="card-columns">
<#list items as item>
    <a href="/item/${item.id}" class="product-link">
        <div class="card text-white bg-success">
        <img src="/images/${item.previewImageId}"
             class="card-img-top">
        <div class="card-body">
            <h5 class="card-title">${item.name}</h5>
            <p class="card-text">${item.price} tg.</p>
        </div>
        <div class="card-footer">
            ${item.city}
        </div>
        </div>
    </a>
    <hr>
<#else>
    <h1 style="text-align: center">No ads found</h1>
</#list>
    </div>
</@t.template>
<#import "blocks/template.ftlh" as t>
<@t.template user>
<h4>Item details</h4>
    <div class="card-columns">
<#list images as img>
<#--    <img src="/images/${img.id}" height="60px"/><br><br>-->
    <div class="card text-white bg-primary">
    <img src="/images/${img.id}"
         class="card-img-top">
    </div>
</#list>
    </div>
    <hr>
    <p><h1 style="text-align: center">${item.name} | ${item.price} tg.</h1></p>
    <p style="text-align: center; font-weight: bold">${item.description}</p>
    <p style="text-align: center; font-weight: bold">Owner's phone number: <b>${item.user.phoneNumber}</b></p>

    <hr>
    <p style="text-align: center; font-weight: bold"> Posted by:
        <a href="/user/${item.user.id}">
           ${item.user.name}
        </a>
    </p>

</@t.template>
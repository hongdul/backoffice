package com.example.backoffice.store.model

import com.example.backoffice.menu.model.Menu
import com.example.backoffice.store.dto.StoreResponse
import jakarta.persistence.*

@Entity
@Table(name = "Store")
class Store (
    @Column(name = "name", nullable = false)
    var name : String,

    @Column(name = "address", nullable = false)
    var address : String,

    @Column(name = "phone", nullable = false)
    var phone : String,

    @Column(name = "description")
    var description : String,

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status : StoreStatus,

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var menu : MutableList<Menu> = mutableListOf(),

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    val user : User

    // 여기서, store와 user는 ERD 상 N : 1 관계로 표시되어 있어서 일단 작성함
    // 아직 user와 menu가 작성되어 있지 않기 때문에 빨간불이 뜰 것으로 보임
)
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null
}
fun Store.toResponse() : StoreResponse{
    return StoreResponse(
        name = name,
        address = address,
        phone = phone,
        status = status,
        userid = id,
        description = description
    )
}
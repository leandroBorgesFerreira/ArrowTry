package br.com.leandro.arrowtry.superHeroes.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import br.com.leandro.arrowtry.R
import br.com.leandro.arrowtry.superHeroes.di.SuperHeroesContext
import br.com.leandro.arrowtry.superHeroes.presentation.getSuperHeroes
import br.com.leandro.arrowtry.superHeroes.presentation.heroClick
import br.com.leandro.arrowtry.superHeroes.view.adapter.HeroesCardAdapter
import br.com.leandro.arrowtry.superHeroes.view.viewmodel.SuperHeroViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton

class MainActivity : AppCompatActivity(), HeroesView {

    private val heroesList : MutableList<SuperHeroViewModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupList(heroesList)
    }

    private fun setupList(listItems : List<SuperHeroViewModel>) {
        heroesListRV.layoutManager = LinearLayoutManager(this)
        heroesListRV.adapter = HeroesCardAdapter(listItems, {
            heroClick()
        })
    }

    override fun onResume() {
        super.onResume()
        getSuperHeroes().run(SuperHeroesContext.GetHeroesContext(this, this))
    }

    override fun showNotFoundError() {
        runOnUiThread {
            alert("Not found!!") {
                yesButton { }
            }.show()
        }
    }

    override fun showGenericError() {
        runOnUiThread {
            alert("Generic error!!") {
                yesButton { }
            }.show()
        }
    }

    override fun showAuthenticationError() {
        runOnUiThread {
            alert("Auth error!!") {
                yesButton { }
            }.show()
        }
    }

    override fun drawHeroes(heroes: List<SuperHeroViewModel>) {
        runOnUiThread {
            heroesList.addAll(heroes)
            heroesListRV.adapter.notifyDataSetChanged()
        }
    }
}

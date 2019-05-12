package projekt.e.daftapchallenge


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item.view.*

class ListAdapter(val results : ArrayList<ResultModel>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, p0, false))
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.score.text = results.get(p1).score.toString()
        p0.date.text = results.get(p1).date
    }

    override fun getItemCount(): Int {
        return results.size
    }


}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val score = view.score
    val date = view.date
}
// ExpenseAdapter.kt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s13.gowallet.R

class ExpenseAdapter(private val expenses: List<Expense>) : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    inner class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryTextView: TextView = itemView.findViewById(R.id.tvCategory)
        private val priceTextView: TextView = itemView.findViewById(R.id.tvPrice)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.tvDescription)
        private val dateTextView: TextView = itemView.findViewById(R.id.tvDate)

        fun bind(expense: Expense) {
            categoryTextView.text = "${expense.category}"
            priceTextView.text = "${expense.price}"
            descriptionTextView.text = "${expense.description}"
            dateTextView.text = "${expense.date}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_expense, parent, false)
        return ExpenseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.bind(expenses[position])
    }

    override fun getItemCount(): Int {
        return expenses.size
    }
}

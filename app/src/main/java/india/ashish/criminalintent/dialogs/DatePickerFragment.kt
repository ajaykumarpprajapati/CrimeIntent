package india.ashish.criminalintent.dialogs

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

private const val ARG_DATE = "date"

class DatePickerFragment : DialogFragment(){

    interface CallBacks{
        fun onDateSelected(date: Date)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dateListener = DatePickerDialog.OnDateSetListener{
            _: DatePicker, year: Int, month: Int, day: Int ->

            val resultDate: Date = GregorianCalendar(year, month, day).time

            targetFragment?.let{fragment ->
                (fragment as CallBacks).onDateSelected(resultDate)

            }
        }

        val date = arguments?.getSerializable(ARG_DATE) as Date
        val calender = Calendar.getInstance()
        calender.time = date
        val intialYear = calender.get(Calendar.YEAR)
        val intialMonth = calender.get(Calendar.MONTH)
        val intialDay = calender.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(
            requireContext(),
            dateListener,
            intialYear,
            intialMonth,
            intialDay
        )
    }


    companion object{
        fun newInstance(date: Date): DatePickerFragment{
            val args = Bundle().apply {
                putSerializable(ARG_DATE, date)
            }

            return DatePickerFragment().apply {
                arguments = args
            }
        }


    }
}